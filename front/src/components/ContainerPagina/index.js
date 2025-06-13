export default function ContainerPagina({ children }) {
    return (
        <div className="container-fluid">
            <div className="row justify-content-center bg-dark">
                <div className="col-12 col-sm-12 col-md-6">
                    {children}
                </div>
            </div>
        </div>
    );
}