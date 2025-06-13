import {useState} from "react"
import {Link, useNavigate} from "react-router-dom";
import {authService} from "../../service/authService";
import {setToken} from '../../redux/authSlice'
import {useDispatch} from 'react-redux';
import {setInfo} from "../../redux/infoSlice";
export default function Login() {

    const [usuarioInformado, setUsuarios] = useState("");
    const [senha, setSenha] = useState("");
    const navigate = useNavigate();

    const dispatch = useDispatch();

    const loginSubmit = async (e) => {
        e.preventDefault();
        try {
            const responseAxios = await authService.login({usuario: usuarioInformado, senha: senha})

            if (responseAxios?.token) {
                dispatch(setToken({
                        token: responseAxios.token,
                        logado: true,
                        cnpj: responseAxios.cnpj,
                    }));
                dispatch(setInfo({
                    usuario: responseAxios.usuario,
                    verba: responseAxios.verba
                }));
                navigate("/");
            } else {
                alert("Usuário ou senha não estão certos")
            }
        } catch (error) {
            alert("Erro de conexão com o servidor")
        }
        ;
    }

    return (
        <div className="d-flex justify-content-center align-items-center vh-100"
             style={{
                 backgroundImage: "url('https://br.freepik.com/fotos-gratis/belo-conceito-de-criptomoeda_22126353.htm#fromView=search&page=1&position=42&uuid=aff52d91-ab3e-4138-8330-9f66704dfa6f&query=finan%C3%A7as')",
                 backgroundSize: "cover",
                 backgroundPosition: "center",
             }}
        >
            <form
                onSubmit={loginSubmit}
                className="p-4 bg-dark text-light rounded shadow"
                style={{maxWidth: "500px", width: "100%"}}
            >
                <h1 className="text-center fw-bold mt-2">Login</h1>
                <div className="mt-4">
                    <label className="text-light">Usuário: </label>
                    <input
                        type="text"
                        value={usuarioInformado}
                        onChange={(e) => setUsuarios(e.target.value)}
                        className="form-control"
                    />
                </div>
                <div className="mt-3">
                    <label className="text-light">Senha: </label>
                    <input
                        type="password"
                        value={senha}
                        onChange={(e) => setSenha(e.target.value)}
                        className="form-control"
                    />
                </div>
                <button
                    type="submit"
                    className="btn btn-primary mt-4 w-100"
                >
                    Entrar
                </button>
                <p className="mt-3 text-center">
                    Não possui uma conta?
                    <Link to="/cadastrar" className="text-decoration-underline text-light">Cadastre-se</Link>
                </p>
            </form>
        </div>
    )
}