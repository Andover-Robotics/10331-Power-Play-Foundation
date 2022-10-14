/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package com.qualcomm.hardware.lynx.commands.core;

import com.qualcomm.hardware.lynx.LynxModuleIntf;
import com.qualcomm.hardware.lynx.commands.LynxDatagram;

import java.nio.ByteBuffer;

/**
 * LynxI2cReadStatusQueryResponse is the message that ultimately finally returns data
 * read from I2C
 */
public class LynxI2cReadStatusQueryResponse extends LynxI2cResponse
    {
    //----------------------------------------------------------------------------------------------
    // State
    //----------------------------------------------------------------------------------------------

    public final static int cbPayload = 2;

    private byte    cbRead;
    private byte[]  payload;

    //----------------------------------------------------------------------------------------------
    // Construction
    //----------------------------------------------------------------------------------------------

    public LynxI2cReadStatusQueryResponse(LynxModuleIntf module)
        {
        super(module);
        }

    public LynxI2cReadStatusQueryResponse(LynxModuleIntf module, int cbExpected)
        {
        super(module);
        this.payload = new byte[cbExpected];
        }

    //----------------------------------------------------------------------------------------------
    // Accessors
    //----------------------------------------------------------------------------------------------

    public byte[] getBytes()
        {
        return this.payload;
        }

    //----------------------------------------------------------------------------------------------
    // Operations
    //----------------------------------------------------------------------------------------------

    @Override
    public byte[] toPayloadByteArray()
        {
        ByteBuffer buffer = ByteBuffer.allocate(cbPayload).order(LynxDatagram.LYNX_ENDIAN);
        buffer.put(this.i2cStatus);
        buffer.put(this.cbRead);
        buffer.put(this.payload);
        return buffer.array();
        }

    @Override
    public void fromPayloadByteArray(byte[] rgb)
        {
        ByteBuffer buffer = ByteBuffer.wrap(rgb).order(LynxDatagram.LYNX_ENDIAN);
        this.i2cStatus = buffer.get();
        this.cbRead = buffer.get();
        this.payload = new byte[this.cbRead];
        buffer.get(this.payload);
        }
    }
