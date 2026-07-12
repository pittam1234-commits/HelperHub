import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router";
import api from "../api/axiosConfig";

function Reviews() {
  const location = useLocation();
  const navigate = useNavigate();

  const worker = location.state?.worker;

  const [rating, setRating] = useState(5);
  const [comment, setComment] = useState("");
  const [reviews, setReviews] = useState([]);
  const [averageRating, setAverageRating] = useState(0);
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false);

  const userId = localStorage.getItem("userId");

  useEffect(() => {
    if (worker?.id) {
      loadReviews();
      loadAverageRating();
    }
  }, [worker]);

  const loadReviews = async () => {
    try {
      const response = await api.get(
        `/reviews/worker/${worker.id}`
      );

      setReviews(response.data);
    } catch (error) {
      console.error("REVIEW LOAD ERROR:", error);
    }
  };

  const loadAverageRating = async () => {
    try {
      const response = await api.get(
        `/reviews/average/${worker.id}`
      );

      setAverageRating(response.data);
    } catch (error) {
      console.error("AVERAGE RATING ERROR:", error);
    }
  };

  const submitReview = async (e) => {
    e.preventDefault();

    setMessage("");

    if (!userId) {
      alert("Please login again");
      navigate("/login");
      return;
    }

    if (!worker?.id) {
      setMessage("Worker not found");
      return;
    }

    const reviewData = {
      userId: Number(userId),
      workerId: Number(worker.id),
      rating: Number(rating),
      comment: comment
    };

    console.log("REVIEW DATA:", reviewData);

    try {
      setLoading(true);

      const response = await api.post(
        "/reviews",
        reviewData
      );

      console.log("REVIEW RESPONSE:", response.data);

      setComment("");
      setRating(5);

      setMessage("Review Submitted Successfully");

      await loadReviews();
      await loadAverageRating();
    } catch (error) {
      console.error("REVIEW ERROR:", error);

      const status = error.response?.status;

      const errorMessage =
        error.response?.data?.message ||
        error.response?.data?.error ||
        error.message ||
        "Review Failed";

      setMessage(
        `Review Failed (${status || "No Status"}): ${errorMessage}`
      );
    } finally {
      setLoading(false);
    }
  };

  if (!worker) {
    return (
      <div className="container">
        <h2>Reviews</h2>

        <p>Please select a worker.</p>

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
      <h2>Worker Reviews</h2>

      <div className="worker-card">
        <h3>{worker.name}</h3>

        <p>Category: {worker.category}</p>

        <p>City: {worker.city}</p>

        <p>
          Average Rating: ⭐{" "}
          {Number(averageRating).toFixed(1)}
        </p>
      </div>

      <h3>Write Review</h3>

      <form onSubmit={submitReview}>
        <select
          value={rating}
          onChange={(e) =>
            setRating(e.target.value)
          }
        >
          <option value="5">⭐⭐⭐⭐⭐ - 5</option>
          <option value="4">⭐⭐⭐⭐ - 4</option>
          <option value="3">⭐⭐⭐ - 3</option>
          <option value="2">⭐⭐ - 2</option>
          <option value="1">⭐ - 1</option>
        </select>

        <textarea
          placeholder="Write your review"
          value={comment}
          onChange={(e) =>
            setComment(e.target.value)
          }
          required
        />

        <button
          type="submit"
          disabled={loading}
        >
          {loading
            ? "Submitting..."
            : "Submit Review"}
        </button>
      </form>

      {message && (
        <p>
          {message}
        </p>
      )}

      <h3>Customer Reviews</h3>

      {reviews.length === 0 ? (
        <p>No reviews available.</p>
      ) : (
        reviews.map((review) => (
          <div
            className="worker-card"
            key={review.id}
          >
            <h4>
              {review.user?.name || "Customer"}
            </h4>

            <p>
              Rating: {"⭐".repeat(review.rating)}
            </p>

            <p>{review.comment}</p>

            <p>
              Date: {review.reviewDate}
            </p>
          </div>
        ))
      )}

      <button
        type="button"
        onClick={() => navigate("/workers")}
      >
        Back to Workers
      </button>
    </div>
  );
}

export default Reviews;