import React, { Component } from 'react';
import { connect } from 'react-redux';
import { editActor, fetchActor } from '../../actions';
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
            name: ''
        }
    }

    componentDidMount() {
        const id = this.props.match.params.id;
        this.props.fetchActor(id)
    }

    onSubmit = (e) => {
        console.log(this.state)
        e.preventDefault()
        if (this.state.activeItem === 'actor') {
            // this.props.editActor({name: this.state.name});
        }
        console.log(this.state.name)
    };

    handleChange = (value) => {
        this.setState({inputValue: value});
    }

    render () {
        return (
            <AdminContainer activeItem={this.state.activeItem}>
                <AdminInternalHeader title={this.state.headerTitle} link="" />
                <DefaultForm onSubmit={this.onSubmit} onInputChange={this.handleChange} />
            </AdminContainer>
        );   
    }
};

const mapStateToProps = (state, ownProps) => {
    return { actor: state.actors[ownProps.match.params.id] };
};

export default withRouter(connect(
    mapStateToProps,
    { editActor, fetchActor }
)(EditDefault));
  