import React, { useState } from 'react';

export const ModalForm = ({ onFormSubmit }) => {
    const [open, setOpen] = useState(false);
    const [formData, setFormData] = useState({
        username: '',
        email: '',
        password: '',
        grannyName: '',
    });

    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const handleSubmit = event => {
        // so that form submit does not reload the page
        event.preventDefault();
        onFormSubmit(formData);
        handleClose();
    };

    const handleInputChange = event => {
        // copies all properties from formData, and sets the property with computed property name []
        setFormData({ ...formData, [event.target.name]: event.target.value });
    };

    return (
        <div>
            <button onClick={handleOpen}>Open Modal</button>
            {open && (
                <div style={{ position: 'fixed', top: 0, left: 0, bottom: 0, right: 0, background: 'rgba(0, 0, 0, 0.5)' }}>
                    <div style={{ background: '#fff', padding: 20, position: 'absolute', top: '50%', left: '50%', transform: 'translate(-50%, -50%)' }}>
                        <form onSubmit={handleSubmit}>
                            <div>
                                <label htmlFor="username">Username:</label>
                                <input type="text" id="username" name="username" 
                                       value={formData.username}
                                       onChange={handleInputChange} />
                            </div>
                            <div>
                                <label htmlFor="email">Email:</label>
                                <input type="text" id="email" name="email"
                                       value={formData.email}
                                       onChange={handleInputChange} />
                            </div>
                            <div>
                                <label htmlFor="password">Password:</label>
                                <input type="password" id="password" name="password"
                                       value={formData.password}
                                       onChange={handleInputChange} />
                            </div>
                            <div>
                                <label htmlFor="grannyName">Granny Name:</label>
                                <input type="text" id="grannyName" name="grannyName"
                                       value={formData.grannyName}
                                       onChange={handleInputChange} />
                            </div>
                            <button type="submit">Submit</button>
                            <button type="button" onClick={handleClose}>Close</button>
                        </form>
                    </div>
                </div>
            )}
        </div>
    );

};
