import React, { useState } from 'react';
import { Header, Accordion, Icon, Divider, Form, Input, Button } from 'semantic-ui-react'

const Filter = () => {
    const [activeIndex, setActiveIndex] = useState(-1)

    const handleClick = (e, titleProps) => {
        const { index } = titleProps
        const newIndex = activeIndex === index ? -1 : index
        setActiveIndex(newIndex)
    }

    const renderForm = (inputPlaceholder) => {
        return (
            <Form style={{ display: 'flex' }}>
                <Input type="text" name="term" placeholder={inputPlaceholder} style={{ marginRight: '5px' }} />
                <Button type="submit" icon>
                    <Icon name="angle right" />
                </Button>
            </Form>
        );
    }

    return (
        <>
            <Header as="h5" textAlign="center">Filtro</Header>
            <Divider />
            <Accordion>
                <Accordion.Title
                    active={activeIndex === 0}
                    index={0}
                    onClick={handleClick}
                >
                    <Icon name='dropdown' />
                    Nome
                </Accordion.Title>
                <Accordion.Content active={activeIndex === 0}>
                    { renderForm('Nome') }
                </Accordion.Content>

                <Accordion.Title
                    active={activeIndex === 1}
                    index={1}
                    onClick={handleClick}
                >
                    <Icon name='dropdown' />
                    Diretor
                </Accordion.Title>
                <Accordion.Content active={activeIndex === 1}>
                    { renderForm('Diretor') }
                </Accordion.Content>

                <Accordion.Title
                    active={activeIndex === 2}
                    index={2}
                    onClick={handleClick}
                >
                    <Icon name='dropdown' />
                    Ator
                </Accordion.Title>
                <Accordion.Content active={activeIndex === 2}>
                    { renderForm('Ator') }
                </Accordion.Content>

                <Accordion.Title
                    active={activeIndex === 3}
                    index={3}
                    onClick={handleClick}
                >
                    <Icon name='dropdown' />
                    Gênero
                </Accordion.Title>
                <Accordion.Content active={activeIndex === 3}>
                    { renderForm('Gênero') }
                </Accordion.Content>
            </Accordion>
        </>
    );
}

export default Filter;