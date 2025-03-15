import { useState } from "react"
import { useNavigate } from "react-router-dom";
import { UsuarioContext, useUsuarioContext } from "../../contexts/Usuario";
import axios from "axios";


export default function Login() {

    const [usuarioInformado, setUsuarios] = useState("");
    const [senha, setSenha] = useState("");
    const { login } = useUsuarioContext(UsuarioContext);
    const navigate = useNavigate();



    const loginSubmit = async (e) => {
        e.preventDefault();

        try {

            {/*const response = await fetch("https://viacep.com.br/ws/01001000/json/", {
                method: get 

            });

            if (response.ok) {
                alert("Deu ruim")
            }

            const data = await response.json();
        alert(JSON.stringify(data))*/}

            {/*axios.get("https://viacep.com.br/ws/01001000/json/")
            .then ((response) => 

    )*/}

            {/*const responseAxios = await axios.get("https://viacep.com.br/ws/01001000/json/") 

                const data = await responseAxios.json();
alert(JSON.stringify(data))*/}

            if (usuarioInformado === "Heron" && senha === "1") {
                login({ nome: usuarioInformado, usuarioInformado, logado: true });
                navigate("/")
            }
            else {
                alert("Ta errado")
            }

        } catch (error) {
            alert("Erro de conexão com o servidor")
        }




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
            </div>
        </form>
    )
}