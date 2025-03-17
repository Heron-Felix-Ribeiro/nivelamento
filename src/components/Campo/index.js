export default function Campo({ nome, valor, mudanca, tipo, obrigatorio }) {

    return (
        <div className="form-group mb-2">
            <label>{nome}</label>
            <div>
                <input
                     type={tipo}
                     name={nome}
                     value={valor}            
                     onChange={mudanca}      
                     required={obrigatorio}   
                     className="form-control"
                />
            </div>
        </div>
    );
}