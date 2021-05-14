import React from 'react';
import { Header, Rating } from 'semantic-ui-react';
import api from '../services/api';

class MovieInfo extends React.Component {

    constructor (props){
        super(props);
        this.openRdfXml = this.openRdfXml.bind(this);      
    }

    openRdfXml() {
        api.get(`/open/movie/data/${this.props.id}`, {
            "Content-Type": "application/xml; charset=utf-8"
        }).then((response) => {
            const blob = new Blob([response.data], {type: 'text/xml'});
            const url = URL.createObjectURL(blob);
            window.open(url);
            URL.revokeObjectURL(url);
        }).catch((error) => {
            console.error(error);
        });
    }

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
                <div style={{ display: 'flex', marginBottom: '5px'}}>
                    <p>
                        <label style={{ fontWeight: 'bold', marginRight: '2px' }}>Sinopse:</label>
                        {this.props.synopsis}
                    </p>
                </div>
                <div style={{ marginBottom: '20px'}}>
                    <button
                        style={{
                            color: '#4183c4',
                            textDecoration: 'none',
                            backgroundColor: 'transparent',
                            border: 'none',
                            cursor: 'pointer',
                            padding: '0px'
                        }}
                        onClick={this.openRdfXml}
                    >
                        RDF/XML
                    </button>
                </div>
            </>
        );
    }
}

export default MovieInfo;