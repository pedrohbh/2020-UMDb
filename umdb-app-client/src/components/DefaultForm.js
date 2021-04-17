import React, { Component } from 'react';

import { Button, Icon, Form, Input} from 'semantic-ui-react'

class DefaultForm extends Component  {
    constructor(props) {
        super(props)
        this.state = {
            inputValue: this.props.initialValue || ''
        }
    }

    onSubmit = formValues => {
        this.props.onSubmit(formValues)
    }

    handleChange = e => {
        this.setState({ inputValue: e.target.value })
        this.props.onInputChange(this.state.inputValue)
    }

    render () {
        return (
            <Form onSubmit={this.onSubmit}>
                <Form.Field>
                    <label>Nome</label>
                    <Input placeholder='Nome' name="name" value={this.state.inputValue} onChange={this.handleChange} />
                </Form.Field>
                <Button icon type="submit" labelPosition='left'>
                    <Icon name={this.props.buttonIcon} />
                    {this.props.buttonLabel}
                </Button>
            </Form>
        );   
    }
};

export default DefaultForm;
  