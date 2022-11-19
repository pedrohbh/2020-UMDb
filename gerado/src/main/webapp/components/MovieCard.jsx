import React from 'react';
import ReactDOM from 'react-dom';

class MovieCard extends React.Component
{
	constructor(props)
	{
		super(props);		
		this.state = {imageSource : "", year : ""};		
	}

	
	
	render() {  
      return (
        <div><img src={imageSource}></img><Card.Content ></Card.Content><Card.Header ></Card.Header><Card.Meta ></Card.Meta><span id="year">{{ year }}</span><Card.Content ></Card.Content><Rating ></Rating></div>
      );
    }
  }

}