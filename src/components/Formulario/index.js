import { useState } from "react";

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
        <form onSubmit={handleSubmit} className="form-container m-2">
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
            <div className="d-grip gap-2 text-center p-2">
                <button type="submit" className="btn btn-primary btn-lg mt-3">
                    {botaoTexto || "Enviar"}
                </button>
            </div>

        </form>
    );
}
