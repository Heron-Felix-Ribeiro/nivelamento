import { createContext, useContext, useEffect, useState } from "react";
import axios from "axios";
import api from "../axiosConfig";

export const UsuarioContext = createContext();

UsuarioContext.displayName = "Usuario";

export default function UsuarioProvider({ children }) {
    const [usuario, setUsuario] = useState({});
    const [transacoes, setTransacoes] = useState([]);
    const [saldo, setSaldo] = useState(0);

    console.log("Usuario:", usuario);

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
                    const response = await api.get(`/transacao/listar/${usuario.id}`);
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