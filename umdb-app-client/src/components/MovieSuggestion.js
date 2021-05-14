import React, { Component } from 'react';
import { Header, Divider, Icon } from 'semantic-ui-react'
import api from '../services/api';

class Filter extends Component{
    constructor(props) {
        super(props)
        this.state = {
            suggestions: []
        }
    }

    componentDidMount() {
       this.setSuggestions()
    }

    setSuggestions() {
        api.get("/close/user/suggestion/")
        .then((response) => {
            this.setState({ suggestions: response.data })
        }).catch((error) => {
            console.error(error);
        });
    }

    renderSuggestions(){
        return this.state.suggestions.map((suggestion, index) =>{
            return(
                <p key={index}>
                    <Icon name="film" /> {suggestion}
                </p>
            )
        })
    }

    render() {
        return (
            <>
                <Header as="h5" textAlign="center">Sugestões</Header>
                <Divider />
                {
                    this.state.suggestions.length > 0 ? (
                        
                        <div>
                            {this.renderSuggestions()}
                        </div>
                    ) : (
                        <div>
                            <p>
                                Ainda não temos sugestões para você.
                            </p>
                            <p>
                                Tente avaliar algum filme para novas sugestões.
                            </p>
                        </div>
                    )
                }
            </>
        );
    }

}

export default Filter;