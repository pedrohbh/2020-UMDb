import React from 'react';
import { Container } from 'semantic-ui-react';

const Footer = () => {
    return (
        <footer style={{ padding: '20px 0px' }}>
            <Container>
                <p style={{ color: '#a1a1a1', textAlign: 'center' }}>© Copyright 2020 - UMDb® - Todos os direitos reservados.</p>
            </Container>
        </footer>
    );
}

export default Footer;