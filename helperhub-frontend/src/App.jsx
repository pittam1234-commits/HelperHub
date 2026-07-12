import { Routes, Route } from "react-router";

import Navbar from "./components/Navbar";

import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import AdminLogin from "./pages/AdminLogin";
import Workers from "./pages/Workers";
import Bookings from "./pages/Bookings";
import Payments from "./pages/Payments";
import Reviews from "./pages/Reviews";
import AdminDashboard from "./pages/AdminDashboard";

function App() {
  return (
    <>
      <Navbar />

      <Routes>
        <Route path="/" element={<Home />} />

        <Route
          path="/login"
          element={<Login />}
        />

        <Route
          path="/register"
          element={<Register />}
        />

        <Route
          path="/admin-login"
          element={<AdminLogin />}
        />

        <Route
          path="/workers"
          element={<Workers />}
        />

        <Route
          path="/bookings"
          element={<Bookings />}
        />

        <Route
          path="/payments"
          element={<Payments />}
        />

        <Route
          path="/reviews"
          element={<Reviews />}
        />

        <Route
          path="/admin"
          element={<AdminDashboard />}
        />
      </Routes>
    </>
  );
}

export default App;