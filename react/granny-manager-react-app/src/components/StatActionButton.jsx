function StatActionButton({apiLink, jsonKey, setState, actionText}) {
    async function updateData() {
        console.log(apiLink);
        //TODO: add method + header when backend is changed to put request
        const response = await fetch(apiLink).then(res => res.json());
        // TODO: have a uniform key for stats OR give the key as prop from parent
        const updatedData = response[jsonKey].stringValueOfStat;
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