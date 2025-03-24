export default function BotaoDelete ({ aoDeletar}) {

    return (
        <button
        onclick={aoDeletar}
        className="btn btn danger"
        >
            <label>
                Deletar
            </label>
        </button>
    )

}