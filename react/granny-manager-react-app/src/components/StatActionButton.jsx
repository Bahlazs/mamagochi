

function StatActionButton( { apiLink, text, setState } ) {

    return (
            <button
            className='action-btn'
            onClick={() => {
              fetch(apiLink, {
                method: "PUT",
                headers: {
                  'Content-type': 'application/json'
                }
              })
            } }
            >
              {text}
            </button>
        )
}

export default StatActionButton;