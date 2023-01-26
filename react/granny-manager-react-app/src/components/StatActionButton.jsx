function StatActionButton({apiLink, jsonKey, setState, actionText}) {
    async function updateData() {
        console.log(apiLink);
        const response = await fetch(apiLink, {
            method: "GET",
            headers: {
                'Content-type': 'application/json'
            }
        }).then(res => res.json());
        // TODO: have a uniform key for stats OR give the key as prop from parent
        const updatedData = response[jsonKey];
        setState(updatedData);
    }
    // TODO: fetch doesn't work yet on click.
    return (
        <button
            className='action-btn'
            onClick={updateData}>
            {actionText}
        </button>
    )
}

export default StatActionButton;