import React from 'react';
import { Header, Rating } from 'semantic-ui-react';

const MovieInfo = ({ title, rating, year, director, actors, genres, synopsis }) => {
    return (
        <>
            <Header as="h3">{title}</Header>
            <div style={{ display: 'flex', alignItems: 'center', marginBottom: '5px'}}>
                <label style={{ fontWeight: 'bold', marginRight: '2px' }}>Avaliação:</label>
                <Rating defaultRating={rating} maxRating={5} disabled style={{ marginRight: '5px' }} />
            </div>
            <div style={{ display: 'flex', marginBottom: '5px'}}>
                <label style={{ fontWeight: 'bold', marginRight: '2px' }}>Ano:</label>
                <p>{year}</p>
            </div>
            <div style={{ display: 'flex', marginBottom: '5px'}}>
                <label style={{ fontWeight: 'bold', marginRight: '2px' }}>Diretor:</label>
                <p>{director}</p>
            </div>
            <div style={{ display: 'flex', marginBottom: '5px'}}>
                <label style={{ fontWeight: 'bold', marginRight: '2px' }}>Atores:</label>
                <p>{actors}</p>
            </div>
            <div style={{ display: 'flex', marginBottom: '5px'}}>
                <label style={{ fontWeight: 'bold', marginRight: '2px' }}>Gêneros:</label>
                <p>{genres}</p>
            </div>
            <div style={{ marginBottom: '20px'}}>
                <p>
                    <label style={{ fontWeight: 'bold', marginRight: '2px' }}>Sinopse:</label>
                    {synopsis}
                </p>
            </div>
        </>
    );
}

export default MovieInfo;