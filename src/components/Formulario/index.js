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
        <form onSubmit={handleSubmit} className="container mt-4">
            <div className="row">
                {campos.map((campo) => (
                    <div key={campo.name} className={`col-12 col-sm-6 col-md-4 mb-3`}>
                        <label htmlFor={campo.name} className="form-label">{campo.label}</label>
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
            </div>
            <div className="text-center mt-4 p-3">
                <button type="submit" className="btn btn-primary btn-lg ">
                    {botaoTexto || "Enviar"}
                </button>
            </div>
        </form>
    );
}