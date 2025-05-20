import {BrowserRouter, Navigate, Route, Routes, useLocation} from "react-router-dom";
import Header from "./components/header";
import Footer from "./components/footer";
import Transacoes from "./pages/transacoes";
import Sidebar from "./components/sidebar";
import Despesas from "./pages/despesas";
import Login from "./pages/login";
import CriarTransacao from "./pages/criarTransacao";
import CriarDespesa from "./pages/criarDespesa";
import CadastroUsuario from "./pages/cadastroUsuario";
import AtualizarTransacao from "./pages/atualizarTransacao";
import AtualizarDespesa from "./pages/atualizarDespesa";
import {Provider, useSelector} from "react-redux";
import store from './redux/store'
import Home from "./pages/home";

function PrivateRoute({children}) {
    const token = useSelector(state => state.auth.token);
    const location = useLocation();

    if (!token && location.pathname !== "/auth") {
        return <Navigate to="/auth" replace/>;
    }

    return children;
}

function ProtectedLayout({children}) {
    return (
        <>
            <Header/>
            <div className="d-flex">
                <Sidebar/>
                <div className="flex-grow-1 p-4">
                    {children}
                </div>
            </div>
            <Footer/>
        </>
    )
}

export default function AppRoute() {
    return (
        <Provider store={store}>
            <BrowserRouter>
                <Routes>
                    <Route path="/auth" element={<Login/>}></Route>
                    <Route path="/cadastrar" element={<CadastroUsuario/>}></Route>

                    <Route path="/*"
                           element={
                               <PrivateRoute>
                                   <ProtectedLayout>
                                       <Routes>
                                           <Route path="/" element={<Home/>}></Route>
                                           <Route path="/transacoes" element={<Transacoes/>}></Route>
                                           <Route path="/despesas" element={<Despesas/>}></Route>
                                           <Route path="/criar_transacao" element={<CriarTransacao/>}></Route>
                                           <Route path="/criar_tipo_despesa" element={<CriarDespesa/>}></Route>
                                           <Route path="/atualizar_transacao/:id"
                                                  element={<AtualizarTransacao/>}></Route>
                                           <Route path="/atualizar_despesa/:id" element={<AtualizarDespesa/>}></Route>
                                       </Routes>
                                   </ProtectedLayout>
                               </PrivateRoute>
                           }
                    />
                </Routes>
            </BrowserRouter>
        </Provider>
    )
}
