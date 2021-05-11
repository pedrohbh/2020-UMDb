import React from 'react';
import { Header, Rating } from 'semantic-ui-react';

class MovieInfo extends React.Component {
    render () {
        return (
            <>
                <Header as="h3">{this.props.title}</Header>
                <div style={{ display: 'flex', alignItems: 'center', marginBottom: '5px'}}>
                    <label style={{ fontWeight: 'bold', marginRight: '2px' }}>Avaliação:</label>
                    <Rating rating={this.props.rating}  maxRating={5} disabled style={{ marginRight: '5px' }} />
                </div>
                <div style={{ display: 'flex', marginBottom: '5px'}}>
                    <label style={{ fontWeight: 'bold', marginRight: '2px' }}>Ano:</label>
                    <p>{this.props.year}</p>
                </div>
                <div style={{ display: 'flex', marginBottom: '5px'}}>
                    <label style={{ fontWeight: 'bold', marginRight: '2px' }}>Diretor:</label>
                    <p>{this.props.director}</p>
                </div>
                <div style={{ display: 'flex', marginBottom: '5px'}}>
                    <label style={{ fontWeight: 'bold', marginRight: '2px' }}>Atores:</label>
                    <p>{this.props.actors}</p>
                </div>
                <div style={{ display: 'flex', marginBottom: '5px'}}>
                    <label style={{ fontWeight: 'bold', marginRight: '2px' }}>Gêneros:</label>
                    <p>{this.props.genres}</p>
                </div>
                <div style={{ marginBottom: '20px'}}>
                    <p>
                        <label style={{ fontWeight: 'bold', marginRight: '2px' }}>Sinopse:</label>
                        {this.props.synopsis}
                    </p>
                </div>
            </>
        );
    }
}

export default MovieInfo;