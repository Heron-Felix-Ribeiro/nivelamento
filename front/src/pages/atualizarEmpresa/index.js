import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {empresaService} from "../../service/empresaService";
import Formulario from "../../components/formulario";

export default function EditarEmpresa() {
    const {id} = useParams();
    const [empresa, setEmpresa] = useState({
        nome: "",
        email: "",
        verba: 0,
    });
    const navigate = useNavigate();

    useEffect(() => {

        const fetchEmpresa = async () => {
            try {
                const response = await empresaService.listarUm(id);
                setEmpresa(response.data);
            } catch (error) {
                alert("Erro ao buscar empresa: " + error.message);
            }
        };

        fetchEmpresa();
    }, [id]);

    const handleMudarCampo = (field, value) => {
        setEmpresa((prevEmpresa) => ({
            ...prevEmpresa,
            [field]: value,
        }));
    };

    const handleSubmit = async () => {
        try {
            await empresaService.editar(id, empresa);
            alert("Empresa atualizada com sucesso");
            navigate("/dashboard");
        } catch (error) {
            const errorMessage = error.response?.data?.messages || "Não foi possível atualizar a empresa";
            alert(errorMessage);
        }
    };

    const campos = [
        {name: "nome", label: "Nome", type: "text", required: true},
        {name: "email", label: "E-mail", type: "email", required: true},
        {name: "verba", label: "Verba", type: "number", required: true},
    ];

    return (
        <div className="container bg-dark text-light shadow-lg rounded p-4 mt-5 mb-5" style={{maxWidth: "700px"}}>
            <h1 className="text-center fw-bold mb-4">Atualizar Empresa</h1>
            <Formulario
                campos={campos}
                valores={empresa}
                aoMudarCampo={(field, value) => handleMudarCampo(field, value)}
            />
            <div className="d-flex justify-content-center mt-4">
                <button className="btn btn-success btn-lg" onClick={handleSubmit}>
                    Atualizar
                </button>
            </div>
        </div>
    );
}