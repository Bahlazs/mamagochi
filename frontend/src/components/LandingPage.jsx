import VisitGrannyButton from "./VisitGrannyButton.jsx";
import image from "../assets/whole_granny_nobackground.png"

function LandingPage({userName}) {

        return(
            <div>
              {userName && <VisitGrannyButton/>}
                <div id="image-container">
                  <div id="content">
                    <img src={image} alt="grannypic" id="landing-page-image"/>
                    <div id="welcome-div">
                      Welcome to our page!
                      <br/>
                      Create and take care of your Granny.
                    </div>
                  </div>
                </div>
                <div className="text-card" id="features">
                    <h2 className="card-head">Features</h2>
                    <p className="card-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                        Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                        Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
                        Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                </div>
                <div className="text-card" id="about-us">
                    <h2 className="card-head">About us</h2>
                    <p className="card-text">Ultrices mi tempus imperdiet nulla malesuada pellentesque. Tincidunt praesent semper feugiat nibh sed pulvinar proin gravida. Aliquet enim tortor at auctor urna nunc id. Elit at imperdiet dui accumsan sit amet nulla facilisi morbi. Purus in mollis nunc sed id semper risus. Orci porta non pulvinar neque laoreet suspendisse interdum. Sed id semper risus in hendrerit gravida. Nunc vel risus commodo viverra maecenas accumsan lacus vel. Dictumst quisque sagittis purus sit amet volutpat. Nulla pellentesque dignissim enim sit amet venenatis urna cursus. Molestie a iaculis at erat pellentesque adipiscing commodo elit. Auctor augue mauris augue neque gravida in fermentum et. Diam ut venenatis tellus in. Ante metus dictum at tempor commodo. Ac auctor augue mauris augue neque gravida.

                        Ut aliquam purus sit amet luctus venenatis lectus. Viverra tellus in hac habitasse platea dictumst vestibulum rhoncus. Vel risus commodo viverra maecenas accumsan lacus. Ultrices neque ornare aenean euismod elementum nisi. Tellus in hac habitasse platea. Amet consectetur adipiscing elit ut aliquam purus sit amet luctus. Turpis egestas pretium aenean pharetra magna ac placerat vestibulum lectus. Eu nisl nunc mi ipsum faucibus vitae aliquet nec ullamcorper. Sed arcu non odio euismod lacinia. Feugiat scelerisque varius morbi enim nunc faucibus a. Facilisi etiam dignissim diam quis enim.

                        Urna et pharetra pharetra massa massa ultricies.
                        Eget velit aliquet sagittis id consectetur purus ut faucibus. Pellentesque sit amet porttitor eget dolor morbi non arcu risus.
                        Nullam eget felis eget nunc lobortis mattis aliquam.
                        Mattis rhoncus urna neque viverra.
                        Eget gravida cum sociis natoque penatibus et magnis.
                        Pretium lectus quam id leo. Dictum non consectetur a erat nam at lectus urna duis.
                        Et magnis dis parturient montes nascetur ridiculus. Pulvinar etiam non quam lacus suspendisse faucibus interdum. Dictum sit amet justo donec. Morbi enim nunc faucibus a pellentesque sit amet porttitor eget. Odio pellentesque diam volutpat commodo. Habitasse platea dictumst vestibulum rhoncus est pellentesque elit ullamcorper. Aliquam purus sit amet luctus venenatis. Tempus quam pellentesque nec nam. Sit amet consectetur adipiscing elit ut aliquam purus sit amet. Mattis rhoncus urna neque viverra justo nec ultrices dui sapien.
                        Proin sed libero enim sed faucibus turpis in eu. Vehicula ipsum a arcu cursus.</p>
                </div>
            </div>
        )


}

export default LandingPage