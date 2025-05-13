export default function Toast({mensagem, tipo, duracao}) {

    return (
        <div className={`toast toast-${tipo}`} style={{animaDuration: `${duracao || 2000}ms`}}>
            {mensagem}
        </div>
    )

}