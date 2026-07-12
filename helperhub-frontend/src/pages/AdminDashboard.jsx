import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import api from "../api/axiosConfig";

function AdminDashboard() {
  const navigate = useNavigate();

  const [dashboard, setDashboard] = useState({
    totalUsers: 0,
    totalWorkers: 0,
    totalBookings: 0,
    totalPayments: 0,
    totalRevenue: 0
  });

  const [bookings, setBookings] = useState([]);
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    loadAdminData();
  }, []);

  const loadAdminData = async () => {
    try {
      setLoading(true);

      const dashboardResponse = await api.get(
        "/admin/dashboard"
      );

      const bookingResponse = await api.get(
        "/bookings"
      );

      console.log(
        "DASHBOARD:",
        dashboardResponse.data
      );

      console.log(
        "BOOKINGS:",
        bookingResponse.data
      );

      setDashboard(dashboardResponse.data);
      setBookings(bookingResponse.data);

    } catch (error) {
      console.error("ADMIN ERROR:", error);

      if (error.response?.status === 401) {
        localStorage.clear();
        navigate("/login");
        return;
      }

      setMessage(
        error.response?.data?.message ||
        "Failed to load admin dashboard"
      );
    } finally {
      setLoading(false);
    }
  };

  const approveBooking = async (id) => {
    try {
      await api.put(
        `/bookings/${id}/approve`
      );

      alert("Booking Approved");

      await loadAdminData();

    } catch (error) {
      console.error(
        "APPROVE ERROR:",
        error
      );

      alert(
        error.response?.data?.message ||
        "Approve Failed"
      );
    }
  };

  const rejectBooking = async (id) => {
    try {
      await api.put(
        `/bookings/${id}/reject`
      );

      alert("Booking Rejected");

      await loadAdminData();

    } catch (error) {
      console.error(
        "REJECT ERROR:",
        error
      );

      alert(
        error.response?.data?.message ||
        "Reject Failed"
      );
    }
  };

  if (loading) {
    return (
      <div className="container">
        <h2>Loading Admin Dashboard...</h2>
      </div>
    );
  }

  return (
    <div className="container">

      <h1>HelperHub Admin Dashboard</h1>

      {message && (
        <p style={{ color: "red" }}>
          {message}
        </p>
      )}

      <div className="dashboard-grid">

        <div className="dashboard-card">
          <h3>Total Users</h3>
          <h1>{dashboard.totalUsers}</h1>
        </div>

        <div className="dashboard-card">
          <h3>Total Workers</h3>
          <h1>{dashboard.totalWorkers}</h1>
        </div>

        <div className="dashboard-card">
          <h3>Total Bookings</h3>
          <h1>{dashboard.totalBookings}</h1>
        </div>

        <div className="dashboard-card">
          <h3>Total Payments</h3>
          <h1>{dashboard.totalPayments}</h1>
        </div>

        <div className="dashboard-card">
          <h3>Total Revenue</h3>

          <h1>
            ₹
            {Number(
              dashboard.totalRevenue
            ).toFixed(2)}
          </h1>
        </div>

      </div>

      <h2>Booking Management</h2>

      {bookings.length === 0 ? (
        <p>No bookings available.</p>
      ) : (
        bookings.map((booking) => (

          <div
            className="worker-card"
            key={booking.id}
          >

            <h3>
              Booking #{booking.id}
            </h3>

            <p>
              Customer:{" "}
              {booking.user?.name}
            </p>

            <p>
              Email:{" "}
              {booking.user?.email}
            </p>

            <p>
              Worker:{" "}
              {booking.worker?.name}
            </p>

            <p>
              Category:{" "}
              {booking.worker?.category}
            </p>

            <p>
              Date:{" "}
              {booking.bookingDate}
            </p>

            <p>
              Time:{" "}
              {booking.bookingTime}
            </p>

            <p>
              Status:{" "}
              <strong>
                {booking.status}
              </strong>
            </p>

            {booking.status === "PENDING" && (
              <div className="admin-actions">

                <button
                  type="button"
                  onClick={() =>
                    approveBooking(booking.id)
                  }
                >
                  Approve
                </button>

                <button
                  type="button"
                  onClick={() =>
                    rejectBooking(booking.id)
                  }
                >
                  Reject
                </button>

              </div>
            )}

          </div>

        ))
      )}

    </div>
  );
}

export default AdminDashboard;