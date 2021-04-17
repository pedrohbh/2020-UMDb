import React, { Component } from 'react';
import { connect } from 'react-redux';
import { editActor, fetchActor } from '../../actions/actor';
import { withRouter } from 'react-router-dom';

import DefaultForm from '../../components/DefaultForm'
import AdminContainer from '../../components/AdminContainer'
import AdminInternalHeader from '../../components/AdminInternalHeader'

class EditDefault extends Component  {
    constructor(props) {
        super(props)
        const { headerTitle, activeItem } = this.props
        this.state = {
            headerTitle,
            activeItem,
            name: '',
            id: this.props.match.params.id
        }
    }

    componentDidMount() {
        this.props.fetchActor(this.state.id)
    }

    onSubmit = (e) => {
        e.preventDefault()
        if (this.state.activeItem === 'actor') {
            this.props.editActor(this.state.id, {name: this.state.name});
        }
    };

    handleChange = (value) => {
        this.setState({name: value});
    }

    render () {
        return (
            <AdminContainer activeItem={this.state.activeItem}>
                <AdminInternalHeader title={this.state.headerTitle} link="" />
                <DefaultForm buttonLabel="Editar" buttonIcon="write" initialValue={this.props.actor.name} onSubmit={this.onSubmit} onInputChange={this.handleChange} />
            </AdminContainer>
        );   
    }
};

const mapStateToProps = (state, ownProps) => {
    return { actor: state.actors[ownProps.match.params.id] };
};

export default connect(
    mapStateToProps,
    { editActor, fetchActor }
)(EditDefault);
  