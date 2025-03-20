import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import Menu from "./pages/Menu";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Transacoes from "./pages/Transacoes";
import Sidebar from "./components/Sidebar";
import Despesas from "./pages/Despesas";
import Login from "./pages/Login";
import CriarTransacao from "./pages/CriarTransacao";
import CriarDespesa from "./pages/CriarDespesa";
import UsuarioLogadoProvider, { UsuarioContext } from "./contexts/Usuario"
import { useContext } from "react";

function PrivateRoute({ children }) {
    const usuario = useContext(UsuarioContext)

    if (!usuario["usuario"]?.logado) {

        return (
            <Navigate to="/login" replace />
        )


    }

    return children;
}

function ProtectedLayout({children}) {

    return (
        <>
            <Header />
            <div className="d-flex">
                <Sidebar />
                <div className="flex-grow-1 p-4">
                    {children}
                </div>
            </div>
            <Footer />
        </>
    )
}

export default function AppRoute() {

    return (
        <BrowserRouter>
            <UsuarioLogadoProvider>

                <Routes>
                    <Route path="/login" element={<Login />}></Route>
                    <Route path="/cadastrar" element={<cadastrarUsuario />}></Route>

                    <Route path="/*"
                        element={

                            <PrivateRoute>
                                <ProtectedLayout>
                                    <Routes>

                                        <Route path="/" element={<Menu />}></Route>
                                        <Route path="/transacoes" element={<Transacoes />}></Route>
                                        <Route path="/despesas" element={<Despesas />}></Route>
                                        <Route path="/criar_transacao" element={<CriarTransacao />}></Route>
                                        <Route path="/criar_tipo_despesa" element={<CriarDespesa />}></Route>

                                    </Routes>
                                </ProtectedLayout>
                            </PrivateRoute>

                        }
                    />

                </Routes>

            </UsuarioLogadoProvider>
        </BrowserRouter>
    )
}
