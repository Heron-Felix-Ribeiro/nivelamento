import { IMaskInput } from "react-imask";

export default function Formulario({ campos, aoEnviar, aoMudarCampo, valores }) {
    const handleChange = (input) => {
        let value = input.value;

        if (input.type === "number") {
            value = parseFloat(value) || "";
        }

        if (aoMudarCampo) {
            aoMudarCampo(input.name, value);
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
            <div className="row g-3">
                {campos.map((campo) => (
                    <div key={campo.name} className="col-12 col-sm-12 col-md-4 mb-3">
                        <label htmlFor={campo.name} className="form-label">{campo.label}</label>
                        {campo.type === "select" ? (
                            <select
                                id={campo.name}
                                name={campo.name}
                                value={valores[campo.name]}
                                onChange={(e) => handleChange({ name: campo.name, value: e.target.value })}
                                required={campo.required || false}
                                className="form-control"
                            >
                                <option value="">Selecione...</option>
                                {campo.options.map((option) => (
                                    <option key={option} value={option}>
                                        {option}
                                    </option>
                                ))}
                            </select>
                        ) : campo.mask ? (
                            <IMaskInput
                                id={campo.name}
                                name={campo.name}
                                mask={campo.mask}
                                value={valores[campo.name]}
                                onAccept={(value) => handleChange({ name: campo.name, value })}
                                required={campo.required || false}
                                className="form-control"
                            />
                        ) : (
                            <input
                                id={campo.name}
                                name={campo.name}
                                type={campo.type || "text"}
                                value={valores[campo.name]}
                                onChange={(e) => handleChange({ name: campo.name, value: e.target.value, type: campo.type })}
                                required={campo.required || false}
                                readOnly={campo.readOnly || false}
                                className="form-control"
                            />
                        )}
                    </div>
                ))}
            </div>
        </form>
    );
}