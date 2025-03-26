export default function Formulario({ campos, aoEnviar, botaoTexto, aoMudarCampo, valores }) {
    const handleChange = (e) => {
        let value = e.target.value;

        if (e.target.type === "number") {
            value = parseFloat(value); 
        }
    

        if (aoMudarCampo) {
            aoMudarCampo(e.target.name, value); 
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (aoEnviar) {
            aoEnviar();
        }
    };

    return (
        <form onSubmit={handleSubmit} className="form-container m-2">
            {campos.map((campo) => (
                <div key={campo.name} className="form-group">
                    <label htmlFor={campo.name}>{campo.label}</label>
                    {campo.type === "select" ? (
                        <select
                            id={campo.name}
                            name={campo.name}
                            value={valores[campo.name]}
                            onChange={handleChange}
                            required={campo.required || false}
                            className="form-control"
                        >
                            <option value="">Selecione...</option>
                            {campo.options.map(option => (
                                <option key={option} value={option}>{option}</option>
                            ))}
                        </select>
                    ) : (
                        <input
                            id={campo.name}
                            name={campo.name}
                            type={campo.type || "text"}
                            value={valores[campo.name]}
                            onChange={handleChange}
                            required={campo.required || false}
                            readOnly={campo.readOnly || false}
                            className="form-control"
                        />
                    )}
                </div>
            ))}
            <div className="d-grid gap-2 text-center p-2">
                <button type="submit" className="btn btn-primary btn-lg mt-3">
                    {botaoTexto || "Enviar"}
                </button>
            </div>
        </form>
    );
}