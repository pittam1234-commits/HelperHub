import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import api from "../api/axiosConfig";

function Workers() {
  const [workers, setWorkers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const navigate = useNavigate();

  useEffect(() => {
    loadWorkers();
  }, []);

  const loadWorkers = async () => {
    try {
      const response = await api.get("/workers");

      console.log("Workers:", response.data);

      setWorkers(response.data);
      setError("");
    } catch (error) {
      console.error("Workers Error:", error);

      setError(
        error.response?.data?.message ||
          "Unable to load workers"
      );
    } finally {
      setLoading(false);
    }
  };

  const bookWorker = (worker) => {
    navigate("/bookings", {
      state: {
        worker: worker
      }
    });
  };

  if (loading) {
    return (
      <div className="container">
        <h2>Loading Workers...</h2>
      </div>
    );
  }

  return (
    <div className="container">
      <h2>Available Workers</h2>

      {error && (
        <p style={{ color: "red" }}>
          {error}
        </p>
      )}

      {!error && workers.length === 0 && (
        <p>No workers available.</p>
      )}

      {workers.map((worker) => (
        <div className="worker-card" key={worker.id}>
          <h3>{worker.name}</h3>

          <p>Category: {worker.category}</p>

          <p>City: {worker.city}</p>

          <p>
            Experience: {worker.experience} Years
          </p>

          <p>
            Price: ₹{worker.pricePerHour}
          </p>

          <button
            type="button"
            onClick={() => bookWorker(worker)}
          >
            Book Now
          </button>
        </div>
      ))}
    </div>
  );
}

export default Workers;