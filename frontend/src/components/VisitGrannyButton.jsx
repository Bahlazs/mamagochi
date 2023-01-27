import { Link } from 'react-router-dom';

function VisitGrannyButton() {




    return (
        <div className={"visit-granny-button-container"}>
           <Link to="/visit-granny">
            <button className={"visit-granny-button"}>Visit Granny</button>
           </Link>
        </div>
    );
}

export default VisitGrannyButton