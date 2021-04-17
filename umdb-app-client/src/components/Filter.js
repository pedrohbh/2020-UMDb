import React, { Component } from 'react';
import { Header, Accordion, Icon, Divider, Form, Input, Button } from 'semantic-ui-react'
import api from '../services/api';

class Filter extends Component{
    constructor(props) {
        super(props)
        this.state = {
            activeIndex: -1,
            filterValue: ''
        }
    }

    handleClick = (e, titleProps) => {
        const { index } = titleProps
        const newIndex = this.state.activeIndex === index ? -1 : index
        this.setState({activeIndex: newIndex})
    }

    handleInputChange (event) {
        this.setState({filterValue: event.target.value})
    }

    async handleFilter(event, entity){
        event.preventDefault()
        api.get(`open/movie/filter?entity=${entity}&name=${this.state.filterValue}`)
        .then(({ data }) => {
            this.props.onFilterMovie(data);
        }).catch((error) => {
            console.error(error)
        })
    }


    renderForm (inputPlaceholder, entity){
        return (
            <Form style={{ display: 'flex' }}>
                <Input type="text" name="term" value={this.state.filterValue} placeholder={inputPlaceholder} style={{ marginRight: '5px' }}
                       onChange={(e)=> {this.handleInputChange(e)}}
                />
                <Button type="submit" icon onClick={(e)=>{this.handleFilter(e, entity)}}>
                    <Icon name="angle right" />
                </Button>
            </Form>
        );
    }

    render() {
        return (
            <>
                <Header as="h5" textAlign="center">Filtro</Header>
                <Divider />
                <Accordion>
                    <Accordion.Title
                        active={this.state.activeIndex === 0}
                        index={0}
                        onClick={this.handleClick}
                    >
                        <Icon name='dropdown' />
                        Nome
                    </Accordion.Title>
                    <Accordion.Content active={this.state.activeIndex === 0}>
                        { this.renderForm('Nome', 'movie') }
                    </Accordion.Content>

                    <Accordion.Title
                        active={this.state.activeIndex === 1}
                        index={1}
                        onClick={this.handleClick}
                    >
                        <Icon name='dropdown' />
                        Diretor
                    </Accordion.Title>
                    <Accordion.Content active={this.state.activeIndex === 1}>
                        { this.renderForm('Diretor', 'director') }
                    </Accordion.Content>

                    <Accordion.Title
                        active={this.state.activeIndex === 2}
                        index={2}
                        onClick={this.handleClick}
                    >
                        <Icon name='dropdown' />
                        Ator
                    </Accordion.Title>
                    <Accordion.Content active={this.state.activeIndex === 2}>
                        { this.renderForm('Ator', 'actor') }
                    </Accordion.Content>

                    <Accordion.Title
                        active={this.state.activeIndex === 3}
                        index={3}
                        onClick={this.handleClick}
                    >
                        <Icon name='dropdown' />
                        Gênero
                    </Accordion.Title>
                    <Accordion.Content active={this.state.activeIndex === 3}>
                        { this.renderForm('Gênero', 'genre') }
                    </Accordion.Content>
                </Accordion>
            </>
        );
    }

}

export default Filter;