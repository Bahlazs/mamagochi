function StatActionButton({apiLink, token, setState, actionText}) {
    async function updateData() {
        console.log(token)
        //TODO: add method + header when backend is changed to put request
        const response =
            await fetch(apiLink, {
                method: "GET",
                headers: {
                    'Content-type': 'application/json',
                    Authorization: `Bearer ${token}`
                }
            })
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