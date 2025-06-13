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
import Dashboard from "./pages/admin";
import Empresa from "./pages/empresa";
import Inativos from "./pages/inativos";
import EditarUsuario from "./pages/atualizarUsuario";
import EditarEmpresa from "./pages/atualizarEmpresa";

function PrivateRoute({children}) {
    const token = useSelector(state => state.auth.token);
    const location = useLocation();
    const cnpj = useSelector(state => state.auth.cnpj);

    if (!token && location.pathname !== "/auth") {
        return <Navigate to="/auth" replace />;
    }

    if (!cnpj && location.pathname === "/") {
        return <Navigate to="/dashboard" replace />;
    }

    if (cnpj && (location.pathname === "/dashboard" || location.pathname === "/empresa" || location.pathname === "/inativos"))  {
        return <Navigate to="/" replace />;
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
                                           <Route path="/dashboard" element={<Dashboard/>}></Route>
                                           <Route path="/empresa" element={<Empresa/>}></Route>
                                           <Route path="/inativos" element={<Inativos/>}></Route>
                                           <Route path="/atualizar_usuario/:id" element={<EditarUsuario/>}></Route>
                                           <Route path="/atualizar_empresa/:id" element={<EditarEmpresa/>}></Route>
                                       </Routes>
                                   </ProtectedLayout>
                               </PrivateRoute>
                           }
                    />
                </Routes>
            </BrowserRouter>
        </Provider>
    );
}
