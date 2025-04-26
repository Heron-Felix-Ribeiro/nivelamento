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
import CadastroUsuario from "./pages/CadastroUsuario";
import AtualizarTransacao from "./pages/AtualizarTransacao";
import AtualizarDespesa from "./pages/AtualizarDespesa";
import { Provider, useSelector } from "react-redux";
import store from './redux/store'

function PrivateRoute({ children }) {
    const token = useSelector(state=> state.auth.token)

        return token? children : <Navigate to="/login" replace />

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
            <Provider store={store}>

                <Routes>
                    
                    <Route path="/login" element={<Login />}></Route>
                    <Route path="/cadastrar" element={<CadastroUsuario />}></Route>

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
                                        <Route path="/atualizar_transacao/:id" element={<AtualizarTransacao />}></Route>
                                        <Route path="/atualizar_despesa/:id" element={<AtualizarDespesa />}></Route>

                                    </Routes>
                                </ProtectedLayout>
                            </PrivateRoute>

                        }
                    />

                </Routes>

            </Provider>
        </BrowserRouter>
    )
}
