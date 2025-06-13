import {useState} from "react";
import axios from "axios";
import Formulario from "../../components/formulario";
import {useNavigate} from "react-router-dom";
import {usuarioService} from "../../service/usuarioService";
import {empresaService} from "../../service/empresaService";

export default function CadastroUsuario() {
    const [tipoCadastro, setTipoCadastro] = useState("usuario");
    const [cadastro, setCadastro] = useState({
        usuario: "",
        dataNascimento: "",
        senha: "",
        cnpj: "",
    })
    const [erro, setErro] = useState(null);
    const navigate = useNavigate();


    const cadastroSubmit = async () => {
        try {
            await usuarioService.cadastro(cadastro);

            navigate("/login");
            alert("Conta criada com sucesso");
        } catch (err) {
            const errorMessage = err.response?.data?.messages || 'Não foi possível criar o agente'
            console.error("Erro ao cadastrar:", errorMessage);
        }
    }

    const handleMudarCampo = (field, value) => {
        setCadastro((prevCadastro) => ({
            ...prevCadastro,
            [field]: value
        }));
    };

    const camposUsuario = [
        {name: "usuario", label: "Nome", type: "text", required: true},
        {name: "idade", label: "Data Nascimento", type: "date", required: true},
        {name: "cpf", label: "CPF", type: "text", required: true, mask: "000.000.000-00"},
        {name: "email", label: "E-mail", type: "email", required: true},
        {name: "cnpj", label: "CNPJ", type: "text", required: true, mask: "00.000.000/0000-00"},
        {name: "senha", label: "Senha", type: "password", required: true}
    ]

    return (
        <div className="d-flex justify-content-center align-items-center" style={{
            backgroundImage: "url('https://br.freepik.com/fotos-gratis/belo-conceito-de-criptomoeda_22126353.htm#fromView=search&page=1&position=42&uuid=aff52d91-ab3e-4138-8330-9f66704dfa6f&query=finan%C3%A7as')",
            backgroundSize: "cover",
            backgroundPosition: "center"
        }}
        >
            <div className="container bg-dark text-light shadow-lg rounded p-4 mt-5 mb-5" style={{maxWidth: "700px"}}>
                <div className="text-center mb-4">
                    <h1 className="fw-bold">Cadastro</h1>
                </div>
                <div className="mb-4">
                    <h2 className="fw-bold">Dados</h2>
                    <Formulario
                        campos={camposUsuario}
                        valores={cadastro}
                        aoMudarCampo={(field, value) => handleMudarCampo(field, value)}
                    />
                </div>
                <div className="d-flex justify-content-center">
                    <button className="btn btn-success btn-lg" onClick={cadastroSubmit}>
                        Cadastrar
                    </button>
                </div>
            </div>
        </div>
    );
}