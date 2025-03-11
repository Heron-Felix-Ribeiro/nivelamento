import { BrowserRouter, Route, Routes } from "react-router-dom";
import Menu from "./pages/Menu";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Cadastro from "./pages/cadastro";
import Transacoes from "./pages/Transacoes";
import Sidebar from "./components/Sidebar";
import Despesas from "./pages/Despesas";
import Login from "./pages/Login";
import CriarTransacao from "./pages/CriarTransacao";

export default function AppRoute() {

    return (
        <BrowserRouter>
            <Header />
            <div className="d-flex">
                <Sidebar />

                <div className="flex-grow-1 p-4">

                    <Routes>
                        <Route path="/" element={<Menu />}></Route>
                        <Route path="/cadastro" element={<Cadastro />}></Route>
                        <Route path="/transacoes" element={<Transacoes />}></Route>
                        <Route path="/despesas" element={<Despesas />}></Route>
                        <Route path="/login" element={<Login />}></Route>
                        <Route path="/criar_transacao" element={<CriarTransacao />}></Route>
                    </Routes>

                </div>
            </div>
            <Footer />
        </BrowserRouter>
    )
}