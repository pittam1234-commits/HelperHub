import { useLocation, useNavigate } from "react-router";
import { useState } from "react";
import api from "../api/axiosConfig";

function Payments() {
  const location = useLocation();
  const navigate = useNavigate();

  const booking = location.state?.booking;

  const [paymentMethod, setPaymentMethod] =
    useState("UPI");

  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false);

  const makePayment = async (e) => {
    e.preventDefault();

    setMessage("");

    if (!booking || !booking.id) {
      setMessage("Booking not found");
      return;
    }

    const paymentData = {
      bookingId: Number(booking.id),
      amount: Number(
        booking.worker?.pricePerHour
      ),
      paymentMethod: paymentMethod
    };

    console.log("PAYMENT DATA:", paymentData);

    try {
      setLoading(true);

      const response = await api.post(
        "/payments",
        paymentData
      );

      console.log(
        "PAYMENT RESPONSE:",
        response.data
      );

      alert("Payment Successful");

      navigate("/bookings");
    } catch (error) {
      console.error("PAYMENT ERROR:", error);

      const status = error.response?.status;

      const errorMessage =
        error.response?.data?.message ||
        error.response?.data?.error ||
        error.message ||
        "Payment Failed";

      setMessage(
        `Payment Failed (${status || "No Status"}): ${errorMessage}`
      );
    } finally {
      setLoading(false);
    }
  };

  if (!booking) {
    return (
      <div className="container">
        <h2>Payment</h2>

        <p>Please select a booking first.</p>

        <button
          type="button"
          onClick={() => navigate("/bookings")}
        >
          View Bookings
        </button>
      </div>
    );
  }

  return (
    <div className="container">
      <h2>Make Payment</h2>

      <div className="worker-card">
        <h3>{booking.worker?.name}</h3>

        <p>Booking ID: {booking.id}</p>

        <p>
          Category: {booking.worker?.category}
        </p>

        <p>Date: {booking.bookingDate}</p>

        <p>Time: {booking.bookingTime}</p>

        <p>
          Amount: ₹{booking.worker?.pricePerHour}
        </p>
      </div>

      <form onSubmit={makePayment}>
        <select
          value={paymentMethod}
          onChange={(e) =>
            setPaymentMethod(e.target.value)
          }
        >
          <option value="UPI">
            UPI
          </option>

          <option value="CARD">
            Debit / Credit Card
          </option>

          <option value="NET_BANKING">
            Net Banking
          </option>

          <option value="CASH">
            Cash
          </option>
        </select>

        <button
          type="submit"
          disabled={loading}
        >
          {loading
            ? "Processing..."
            : `Pay ₹${booking.worker?.pricePerHour}`}
        </button>
      </form>

      {message && (
        <p style={{ color: "red" }}>
          {message}
        </p>
      )}
    </div>
  );
}

export default Payments;