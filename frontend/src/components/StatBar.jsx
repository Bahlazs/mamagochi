const StatBar = ( {text, color} ) => {
  return (
      <div className='stat-bar' style={{backgroundColor: color, outline: color}}>
        {text}
      </div>
  )
}

export default StatBar