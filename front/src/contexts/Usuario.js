import { createContext, useContext, useEffect, useState } from "react";
import axios from "axios";

export const UsuarioContext = createContext();

UsuarioContext.displayName = "Usuario";

export default function UsuarioProvider({ children }) {
    const [usuario, setUsuario] = useState("");
    const [transacoes, setTransacoes] = useState([]);
    const [saldo, setSaldo] = useState(0);

    function login(usuarioLogin) {
        setUsuario(usuarioLogin);
    }

    function logout() {
        setUsuario({});
        setTransacoes([]);
        setSaldo(0);
    }

    useEffect(() => {
        const fetchTransacoes = async () => {
            if (usuario?.id) {
                try {
                    const response = await axios.get(`http://localhost:3001/transacao?usuarioId=${usuario.id}`);
                    console.log("Transações carregadas (contexto):", response.data);
                    console.log("Salário do Usuário:", usuario.salario);
                    setTransacoes(response.data);
                } catch (error) {
                    console.error("Erro ao carregar transações:", error);
                }
            }
        };
    
        fetchTransacoes();
    }, [usuario]);
    
    useEffect(() => {
        const saldoInicial = usuario?.salario || 0;
        const totalTransacoes = transacoes.reduce((total, transacao) => {
            return total + Number(transacao.valor); 
        }, 0);
        setSaldo(saldoInicial - totalTransacoes);
    }, [transacoes, usuario]);

    return (
        <UsuarioContext.Provider value={{ usuario, login, logout, transacoes, saldo }}>
            {children}
        </UsuarioContext.Provider>
    );
}

export function useUsuarioContext() {
    return useContext(UsuarioContext);
}