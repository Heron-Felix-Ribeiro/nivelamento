import Formulario from "../../components/formulario";
import {usuarioService} from "../../service/usuarioService";
import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";

export default function EditarUsuario() {
    const {id} = useParams();
    const [usuario, setUsuario] = useState({
        usuario: "",
        email: "",
    });
    const navigate = useNavigate();

    useEffect(() => {
        const fetchUsuario = async () => {
            try {
                const response= await usuarioService.listarUm(id);
                console.log(response);
                setUsuario(response.data);
            } catch (error) {
                alert("Erro ao buscar usuário: " + error.message);
            }
        };

        fetchUsuario();
    }, [id]);

    const handleMudarCampo = (field, value) => {
        setUsuario((prevUsuario) => ({
            ...prevUsuario,
            [field]: value,
        }));
    };

    const handleSubmit = async () => {
        try {
            await usuarioService.editar(id, usuario);
            alert("Usuário atualizado com sucesso");
            navigate("/dashboard");
        } catch (error) {
            const errorMessage = error.response?.data?.messages || "Não foi possível atualizar o usuário";
            alert(errorMessage);
        }
    };

    const campos = [
        {name: "usuario", label: "Usuário", type: "text", required: true},
        {name: "email", label: "E-mail", type: "email", required: true},
    ];

    return (
        <div className="container bg-dark text-light shadow-lg rounded p-4 mt-5 mb-5" style={{maxWidth: "700px"}}>
            <h1 className="text-center fw-bold mb-4">Atualizar Usuário</h1>
            <Formulario
                campos={campos}
                valores={usuario}
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