import { BrowserRouter, Route, Routes } from "react-router-dom";
import Menu from "./pages/Menu";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Cadastro from "./pages/cadastro";
import Transacoes from "./pages/Transacoes";

export default function AppRoute() {

    return (
        <BrowserRouter>
            <Header />
            <Routes>
                <Route path="/" element={<Menu />}></Route>
                <Route path="/cadastro" element={<Cadastro />}></Route>
                <Route path="/transacoes" element={<Transacoes/>}></Route>
            </Routes>
            <Footer />
        </BrowserRouter>
    )
}