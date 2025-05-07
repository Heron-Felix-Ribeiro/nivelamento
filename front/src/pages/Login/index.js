import { useState } from "react"
import { Link, useNavigate } from "react-router-dom";
import { authService } from "../../service/authService";
import {setToken} from '../../redux/authSlice'
import { useDispatch } from 'react-redux';
import store from "../../redux/store";

export default function Login() {

    const [usuarioInformado, setUsuarios] = useState("");
    const [senha, setSenha] = useState("");
    const navigate = useNavigate();

    const dispatch = useDispatch(); 

    const loginSubmit = async (e) => {

        e.preventDefault();
        
        try {
            const responseAxios = await authService.login({usuario: usuarioInformado, senha: senha})

            if (responseAxios?.token ) {
                dispatch(setToken({
                    usuario: usuarioInformado,
                    token: responseAxios.token,
                    logado: true,
                    id: responseAxios.id,
                    salario: responseAxios.salario}))
                navigate("/");

            } else {
                alert("Usuário ou senha não estão certos")
            }

        } catch (error) {
            alert("Erro de conexão com o servidor")
        };

    }

    return (
        <form onSubmit={loginSubmit}>
            <h1 className="text-center fw-bold mt-2">Login</h1>
            <div className="container mt-5 bg-dark pb-5">

                <label className="text-light">Usuario: </label>
                <input type="text" value={usuarioInformado} onChange={(e) => setUsuarios(e.target.value)} className="form-control"></input>
                <label className="text-light">Senha: </label>
                <input type="password" value={senha} onChange={(e) => setSenha(e.target.value)} className="form-control"></input>
                <button type="submit" className="btn btn-primary mt-2 col-md-2 text-center w-100">Entrar</button>
                <Link to="/cadastrar" className="btn btn-secondary mt-2 col-md-2 text-light text-center w-100 p-2">
                    Cadastrar-se
                </Link>

            </div>
        </form>
    )
}