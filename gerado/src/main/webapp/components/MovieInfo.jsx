import React from 'react';
import ReactDOM from 'react-dom';

class MovieInfo extends React.Component
{
	constructor(props)
	{
		super(props);		
		this.state = {year : ""};		
	}

	
	openRdfXml() 
	{
		//TODO To be implemented
	}
	
	
	render() {  
      return (
        <div><Rating ></Rating><p id="year">{{ year }}</p><p id="director">{{ director }}</p>
	
	<button onClick="openRdfXml">openRdfXml</button>
	
	
	<header />
	</div>
      );
    }
  }

}