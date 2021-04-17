import React, { Component } from 'react';
import { connect } from 'react-redux';
import { createActor } from '../../actions';

import DefaultForm from '../../components/DefaultForm'
import AdminContainer from '../../components/AdminContainer'
import AdminInternalHeader from '../../components/AdminInternalHeader'

class CreateDefault extends Component  {
    constructor(props) {
        super(props)
        const { headerTitle, activeItem } = this.props
        this.state = {
            headerTitle,
            activeItem,
            inputValue: ''
        }
        console.log(this.state)
    }

    onSubmit = (e) => {
        e.preventDefault()
        if (this.state.activeItem === 'actor') {
            this.props.createActor({name: this.state.inputValue});
        }
        console.log(this.state.inputValue)
    };

    handleChange = (value) => {
        this.setState({inputValue: value});
    }

    render () {
        return (
            <AdminContainer activeItem={this.state.activeItem}>
                <AdminInternalHeader title={this.state.headerTitle} link="" />
                <DefaultForm buttonLabel="Adicionar" buttonIcon="plus"  onSubmit={this.onSubmit} onInputChange={this.handleChange} />
            </AdminContainer>
        );   
    }
};

export default connect(
    null,
    { createActor }
)(CreateDefault);
  