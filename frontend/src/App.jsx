import MainLayout from "./layout/MainLayout";
import Dashboard from "./pages/Dashboard";
import useTrafficSocket from "./hooks/useTrafficSocket";

function App() {
  useTrafficSocket();

  return (
    <MainLayout>
      <Dashboard />
    </MainLayout>
  );
}

export default App;
