import '../css/Modal.css';

export const Modal = (props: {
    shouldShow: boolean;
    setShouldShow: (shouldShow: boolean) => void;
    children: any;
}) => {
    return (
        <>
            {props.shouldShow && (
                <div className="ModalBackground">
                    <div className="ModalBody">
                        <div>
                            {props.children}
                        </div>
                        <div className="Component-button">
                            <div className="Component-button-row">
                                <button className={"Component-button-input"} onClick={() => props.setShouldShow(false)}>Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            )}
        </>
    );
}