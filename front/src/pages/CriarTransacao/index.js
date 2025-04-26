import axios from "axios";
import { useEffect, useState } from "react";
import Formulario from "../../components/Formulario";
import { useUsuarioContext } from "../../contexts/Usuario";
import { data, useNavigate } from "react-router-dom";

export default function CriarTransacao() {
    const { usuario } = useUsuarioContext();
    const [cadastro, setCadastro] = useState({
        usuario: usuario.id,
        valor: "",
        despesa: "",
        estabelecimento: ""
    })
    const [tiposDespesa, setTipoDespesa] = useState([])
    const navigate = useNavigate();

    const cadastroSubmit = async () => {

        try {
            console.log("Dados do cadastro:", cadastro);
            await axios.post("/transacao", cadastro);
            navigate("/transacoes");
            alert("Transação criada com sucesso");
        } catch (error) {
            alert("Não foi possível cadastrar a transação")
        }

    }

    const carregarTiposDespesa = async() => {

        try{
            const respose = await axios.get(`http://localhost:8080/tipo_despesa/listar/${usuario.id}`);
            console.log(respose)
            setTipoDespesa(respose.data);
        } catch (error) {
            alert("Não foi possível carregar os tipos de despesa"); 
        }

    }

    useEffect(() => {
        carregarTiposDespesa(); 
    }, []);

    const handleMudarCampo = (campo, valor) => {
        setCadastro((prevCadastro) => ({
            ...prevCadastro,
            [campo]: valor
        }));
    };

    return (
        <div className="container bg-dark text-light">
            <h1 className="text-center">Criar Transação</h1>
            <div className="container mt-5 bg-dark pb-5">
                <Formulario
                    campos={[
                        {
                            name: "valor",
                            label: "Valor",
                            type: "number",
                            required: true
                        },
                        { 
                            name: "estabelecimento", 
                            label: "Estabelecimento", 
                            type: "text", 
                            required: true },
                        { 
                            name: "despesa", 
                            label: "Tipo Despesa", 
                            type: "select",
                            options: tiposDespesa.map(despesa => despesa.despesa),  
                            required: false 
                        }
                    ]}
                    valores={cadastro}
                    botaoTexto="Cadastrar"
                    aoMudarCampo={handleMudarCampo}
                    aoEnviar={cadastroSubmit}
                />
            </div>
        </div>
    );
}
