import { Link, useNavigate } from "react-router";

function Navbar() {
  const navigate = useNavigate();

  const token = localStorage.getItem("token");
  const role = localStorage.getItem("role");

  const logout = () => {
    localStorage.clear();
    navigate("/");
    window.location.reload();
  };

  return (
    <nav className="navbar">
      <h2>HelperHub</h2>

      <div className="nav-links">
        <Link to="/">Home</Link>

        {token && (
          <>
            <Link to="/workers">Workers</Link>
            <Link to="/bookings">My Bookings</Link>
          </>
        )}

        {token && role === "ADMIN" && (
          <Link to="/admin">
            Admin Dashboard
          </Link>
        )}

        {!token ? (
          <>
            <Link to="/login">Login</Link>

            <Link to="/register">
              Register
            </Link>

            <Link to="/admin-login">
              Admin Login
            </Link>
          </>
        ) : (
          <button
            type="button"
            onClick={logout}
          >
            Logout
          </button>
        )}
      </div>
    </nav>
  );
}

export default Navbar;