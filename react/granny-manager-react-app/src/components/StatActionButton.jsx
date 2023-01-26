function StatActionButton({apiLink, jsonKey, setState, actionText}) {
    async function updateData() {
        //TODO: add method + header when backend is changed to put request
        const response =
            await fetch(apiLink)
            .then(res => res.json())
            .then((data) => setState(data));
        // TODO: have a uniform key for stats OR give the key as prop from parent
    }
    return (
        <button
            className='action-btn'
            onClick={updateData}>
            {actionText}
        </button>
    )
}

export default StatActionButton;