import { useState } from "react";
import Campo from "../Campo";

export default function Formulario({ campos, aoEnviar, botaoTexto }) {
    const [formData, setFormData] = useState(
        campos.reduce((acc, campo) => {
            acc[campo.name] = "";
            return acc;
        }, {})
    );

    
    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

   
    const handleSubmit = (e) => {
        e.preventDefault();
        if (aoEnviar) {
            aoEnviar(formData);
        }
    };

    return (
        <form onSubmit={handleSubmit} className="form-container">
            {campos.map((campo) => (
                <div key={campo.name} className="form-group">
                    <label htmlFor={campo.name}>{campo.label}</label>
                    <input
                        id={campo.name}
                        name={campo.name}
                        type={campo.type || "text"}
                        value={formData[campo.name]}
                        onChange={handleChange}
                        required={campo.required || false}
                        className="form-control"
                    />
                </div>
            ))}
            <button type="submit" className="btn btn-primary mt-3">
                {botaoTexto || "Enviar"}
            </button>
        </form>
    );
}
