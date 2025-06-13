import {useState} from "react";
import Formulario from "../../components/formulario";
import {empresaService} from "../../service/empresaService";
import {useNavigate} from "react-router-dom";
import axios from "axios";

export default function Empresa() {
    const [cadastro, setCadastro] = useState({
        nome: "",
        dataCriacao: "",
        cnpj: "",
        email: "",
        verba: "",
        ibge: "",
        cep: "",
        bairro: "",
        rua: "",
        numero: "",
    });
    const navigate = useNavigate();

    const handleMudarCampo = (field, value) => {
        setCadastro((prevCadastro) => ({
            ...prevCadastro,
            [field]: value,
        }));
    };

    const camposEmpresa = [
        {name: "nome", label: "Instituição", type: "text", required: true},
        {name: "dataCriacao", label: "Data Criação", type: "date", required: true},
        {name: "cnpj", label: "CNPJ", type: "text", required: true, mask: "00.000.000/0000-00"},
        {name: "email", label: "E-mail", type: "email", required: true},
        {name: "verba", label: "Verba", type: "number", required: true},
    ];

    const camposEndereco = [
        {name: "cep", label: "CEP", type: "text", required: true, mask: "00000-000"},
        {name: "estado", label: "Estado", type: "text", required: true, readOnly: true},
        {name: "cidade", label: "Cidade", type: "text", required: true, readOnly: true},
        {name: "bairro", label: "Bairro", type: "text", required: true, readOnly: true},
        {name: "rua", label: "Rua", type: "text", required: true, readOnly: true},
        {name: "numero", label: "Número", type: "text", required: false}
    ];

    const handleSubmit = async () => {
        try {
            await empresaService.cadastro(cadastro);
            alert("Empresa cadastrada com sucesso");
            navigate("/dashboard");
        } catch (error) {
            const errorMessage = error.response?.data?.messages || 'Não foi possível cadastrar a empresa';
            alert(errorMessage);
        }
    };

    const cepChange = async (cep) => {
        const cepLimpo = cep.replace(/\D/g, "");

        if (cepLimpo.length === 8) {
            try {
                const response = await axios.get(`https://viacep.com.br/ws/${cep}/json/`);
                console.log(response.data);
                if (response.data.erro) {
                    alert("CEP inválido. Por favor, verifique e tente novamente.");
                    return;
                }
                const data = response.data;
                console.log(data);
                setCadastro((prevCadastro) => ({
                    ...prevCadastro,
                    cep: cep,
                    estado: data.uf,
                    cidade: data.localidade,
                    bairro: data.bairro,
                    rua: data.logradouro,
                    ibge: data.ibge
                }));
            } catch (error) {
                console.error("Erro ao buscar CEP:", error);
            }
        } else {
            setCadastro((prevCadastro) => ({
                ...prevCadastro,
                cep: cep
            }));
        }
    };

    return (
        <div className="container bg-dark text-light shadow-lg rounded p-4 mt-5 mb-5" style={{maxWidth: "700px"}}>
            <h1 className="text-center fw-bold mb-4">Cadastro de Empresa</h1>
            <div>
                <h2>Dados</h2>
                <Formulario
                    campos={camposEmpresa}
                    valores={cadastro}
                    aoMudarCampo={(field, value) => handleMudarCampo(field, value)}
                />
            </div>
            <div className="mb-4">
                <h2 className="fw-bold">Endereço</h2>
                <Formulario
                    campos={camposEndereco}
                    valores={cadastro}
                    aoMudarCampo={(field, value) => {
                        handleMudarCampo(field, value);
                        if (field === "cep") {
                            cepChange(value);
                        }
                    }}
                />
            </div>
            <div className="d-flex justify-content-center mt-4">
                <button className="btn btn-success btn-lg" onClick={handleSubmit}>
                    Cadastrar
                </button>
            </div>
        </div>
    );
}