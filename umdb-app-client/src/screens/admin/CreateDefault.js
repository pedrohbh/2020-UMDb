import React from 'react';

import AdminContainer from '../../components/AdminContainer'
import AdminInternalHeader from '../../components/AdminInternalHeader'

import { Button, Icon, Form, Input} from 'semantic-ui-react'

const CreateDefault = ({ headerTitle, activeItem }) => {
    return (
        <AdminContainer activeItem={activeItem}>
            <AdminInternalHeader title={headerTitle} link="" />
            <Form>
                <Form.Field>
                    <label>Nome</label>
                    <Input placeholder='Nome' name="name" />
                </Form.Field>
                <Button icon type="submit" labelPosition='left'>
                    <Icon name='plus' />
                    Adicionar
                </Button>
            </Form>
        </AdminContainer>
    );
};

export default CreateDefault;